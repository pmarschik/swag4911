package swag49.dao;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class StatisticDaoTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("statisticDAO")
    private DataAccessObject<Statistic> statisticDAO;

    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;

    @Autowired @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;


    @Test
    @Transactional
    public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Player player1 = new Player();
		player1.setDeleted(false);
		player1.setOnline(true);
		player1.setUserId(1L);
		player1.setPlays(map);

		player1 = playerDAO.create(player1);

        Player player2 = new Player();
		player2.setDeleted(false);
		player2.setOnline(true);
		player2.setUserId(2L);
		player2.setPlays(map);

		player2 = playerDAO.create(player2);

		Statistic statistic = new Statistic();
        statistic.setName("Most defeats");
        statistic = statisticDAO.create(statistic);

        StatisticEntry entry1 = new StatisticEntry(statistic, 1);
        entry1.setPlayer(player1);
        statistic.getEntries().add(entry1);
        StatisticEntry entry2 = new StatisticEntry(statistic, 2);
        entry2.setPlayer(player2);
        statistic.getEntries().add(entry2);

		statistic = statisticDAO.update(statistic);

        statistic = statisticDAO.get(statistic.getId());
        Iterator<StatisticEntry> iterator = statistic.getEntries().iterator();
        Assert.assertEquals(2, statistic.getEntries().size());
        Assert.assertEquals(new Integer(1), iterator.next().getRanking());
        Assert.assertEquals(new Integer(2), iterator.next().getRanking());
    }

//    @Test
//    public void delete_shouldDelete() throws Exception {
//		Map map = new Map();
//		map.setMaxUsers(5);
//
//		map = mapDAO.create(map);
//
//		Player player = new Player();
//		player.setDeleted(false);
//		player.setOnline(true);
//		player.setUserId(1L);
//		player.setPlays(map);
//
//		player = playerDAO.create(player);
//
//		Message message = new Message();
//		message.setContent("test");
//		message.setReceiver(player);
//		message.setSender(player);
//		message.setSubject("test");
//		message.setSendDate(new Date());
//
//		message = messageDAO.create(message);
//
//		messageDAO.delete(message);
//    }
//
//    @Test
//    public void update_shouldUpdate() throws Exception{
//		Map map = new Map();
//		map.setMaxUsers(5);
//
//		map = mapDAO.create(map);
//
//		Player player = new Player();
//		player.setDeleted(false);
//		player.setOnline(true);
//		player.setUserId(1L);
//		player.setPlays(map);
//
//		player = playerDAO.create(player);
//
//		Message message = new Message();
//		message.setContent("test");
//		message.setReceiver(player);
//		message.setSender(player);
//		message.setSubject("test");
//		message.setSendDate(new Date());
//
//		message = messageDAO.create(message);
//
//		message.setSubject("New Subject");
//
//		messageDAO.update(message);
//    }
}
