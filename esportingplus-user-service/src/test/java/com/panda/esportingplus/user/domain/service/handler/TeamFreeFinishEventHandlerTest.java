//package com.kaihei.esportingplus.user.domain.service.handler;
//
//import com.kaihei.esportingplus.common.event.EventBus;
//import com.kaihei.esportingplus.common.thread.pool.ThreadPoolManager;
//import com.kaihei.esportingplus.user.BaseTest;
//import com.kaihei.esportingplus.user.api.event.TeamFreeEvent;
//import com.kaihei.esportingplus.user.api.event.TeamMember;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import com.kaihei.esportingplus.user.domain.service.MembersUserService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class TeamFreeFinishEventHandlerTest extends BaseTest {
//
//    @Autowired
//    private TeamFreeFinishEventHandler teamFreeFinishEventHandler;
//    @Autowired
//    private MembersUserService membersUserService;
//    {
//        EventBus.create(ThreadPoolManager.INSTANCE.getEventBusExecutor());
//    }
//    @Test
//    public void testHandler() {
//        TeamFreeEvent teamFreeEvent = new TeamFreeEvent();
//        teamFreeEvent.setSlug("afsdfd");
//        teamFreeEvent.setGameType("1");
//        teamFreeEvent.setGameResult(Arrays.asList(0));
//
//        List<TeamMember> members = new ArrayList<>();
//
//        TeamMember baoji1 = new TeamMember();
//        baoji1.setUid("13a8f0e4");
//        baoji1.setLevel("51");
//        baoji1.setRole("0");
//        members.add(baoji1);
//
//        TeamMember baoji2 = new TeamMember();
//        baoji2.setUid("13a8f0e4");
//        baoji2.setLevel("52");
//        baoji2.setRole("1");
//        members.add(baoji2);
//
//
//        teamFreeEvent.setMembers(members);
//        Assert.assertTrue(teamFreeFinishEventHandler.handle(teamFreeEvent));
//    }
//}
