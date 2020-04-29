package com.panda.esportingplus.common.tools;

/**
 *@Description: 哈希Map工具类
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
*/
public final class MapUtils {

    public static void main(String[] args) {
        System.out.println(getMinCapacity(4));
        System.out.println(getMinCapacity(5));
        System.out.println(getMinCapacity(11));
        System.out.println(getMinCapacity(12));

        System.out.println(getMinCapacityNotResize(4));
        System.out.println(getMinCapacityNotResize(5));
        System.out.println(getMinCapacityNotResize(11));
        System.out.println(getMinCapacityNotResize(12));
    }
    /**
     *@Description: 根据目标容量计算临界值
     *@param: [targetEntrySize] 想要的目标容量
     *@return: int 返回扩容的临界值，如果实际容量大于此值则产生扩容，
     * 多次扩容则产生重建哈希表 影响性能
     *@throws:
     *
     * @author shusong.liang
     * @date 2020/03/25 11:12:56
    */
    public static int getMinCapacity(int targetEntrySize)
    {
        return targetEntrySize * 4 / 3 + 3;
    }

    /**
     *@Description: 根据目标容量计算 最终哈希容量
     *@param: [targetEntrySize] 目标容量
     *@return: int 最终哈希容量
     *@throws:
     *
     * @author shusong.liang
     * @date 2020/03/25 11:12:56
     */
    public static int getMinCapacityNotResize(int targetEntrySize)
    {
        if (targetEntrySize <= 0)
        {
            return 0;
        }
        double exponent = Math.log(targetEntrySize) / Math.log(2);
        double defaultInitCapacity = Math.pow(2, Math.ceil(exponent));
        double capacity = defaultInitCapacity;
        if (targetEntrySize >= defaultInitCapacity * 3 / 4 - 1)
        {
            capacity = defaultInitCapacity * 2;
        }
        return (int) capacity;
    }
}

