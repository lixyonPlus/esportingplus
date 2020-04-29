-- lua计数器限流脚本
-- 接口总请求
local totalKey = KEYS[1] --接口限流KEY（2秒过期）
local totalLimit = tonumber(ARGV[1]) --限流大小
local ttl = tonumber(ARGV[2])        --2秒过期
local totalCurrent = tonumber(redis.call('get', totalKey) or "0")
local totalTimes = 0
if totalCurrent + 1 > totalLimit then --如果超出限流大小，返回0
   return 0
else
   totalTimes = redis.call("INCRBY", totalKey,"1")
   if totalCurrent == 1 then --只有第一次的时候设置自增，防止过期时间不间断刷新造成无限自增现象
      redis.call("expire", totalKey,ttl)
   end
end

-- 如果开启单用户限流，用户 + ip + 接口请求：限定每个用户每个IP每分钟只能请求一个接口180次(每秒3次)
if KEYS[2] ~= nil then
   local userKey = KEYS[2] --限流KEY（60秒过期）
   local userCurrent = tonumber(redis.call('get', userKey) or "0")
   if userCurrent + 1 > 180 or userCurrent/60 >= 3 then --如果超出限流大小，返回-1
      return -1
   else
      redis.call("INCRBY", userKey,"1")
      if userCurrent == 1 then --只有第一次的时候设置自增，防止过期时间不间断造成无限自增现象
         redis.call("expire", userKey,60)
      end
   end
end
-- 只返回并发限流数，由于单用户限流与总用户并发限流保持同步，只要有一个达到阈值都返回0
return totalTimes