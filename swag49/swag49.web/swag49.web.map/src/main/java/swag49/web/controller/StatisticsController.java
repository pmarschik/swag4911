package swag49.web.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import swag49.dao.DataAccessObject;
import swag49.model.Statistic;
import swag49.model.StatisticEntry;
import swag49.web.model.StatisticDTO;
import swag49.web.model.StatisticEntryDTO;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
@RequestMapping(value = "/statistics")
@Scope("session")
public class StatisticsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("statisticDAO")
    private DataAccessObject<Statistic, Long> statisticDAO;

    private Map<Long, StatisticDTO> statistics;

    @PostConstruct
    @Transactional("swag49.map")
    public void init() {
        statistics = Maps.newHashMap();
        for (Statistic statistic : statisticDAO.queryByExample(new Statistic())) {
            StatisticDTO dto = new StatisticDTO();
            dto.setId(statistic.getId());
            dto.setName(statistic.getName());
            for (StatisticEntry entry : statistic.getEntries()) {
                StatisticEntryDTO entryDTO = new StatisticEntryDTO();
                entryDTO.setRanking(entry.getRanking());
                entryDTO.setPlayer(entry.getPlayer().getUserId());
                entryDTO.setScore(entry.getScore());
                dto.getEntries().add(entryDTO);
            }
            statistics.put(dto.getId(), dto);
        }
    }

    private StatisticDTO getStatistic(String id) {
        Long longId = Long.valueOf(id);
        return statistics.get(longId);
    }

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {
        map.put("statistics", statistics.values());

        return "statistics";
    }

    @RequestMapping(value = "/index")
    public String statistics() {
        return "redirect:./";
    }

    @RequestMapping(value = "/{id}")
    public String show(@PathVariable("id") String id, Map<String, Object> map) {
        map.put("statistic", getStatistic(id));

        return "statistic";
    }
}
