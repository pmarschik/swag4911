package swag49.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import swag49.dao.DataAccessObject;
import swag49.model.Statistic;
import swag49.web.model.StatisticDTO;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping(value = "/statistics")
@Scope(value = "session")
public class StatisticsController {

    @Autowired
    private MapController mapController;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("statisticDAO")
    private DataAccessObject<Statistic, Long> statisticDAO;

    private Map<Long, StatisticDTO> statistics;

    private Collection<StatisticDTO> getStatistics() {
        if (statistics == null) {
            for (Statistic statistic : statisticDAO.queryByExample(new Statistic())) {
                StatisticDTO dto = new StatisticDTO();
                dto.setId(statistic.getId());
                dto.setName(statistic.getName());
                statistics.put(dto.getId(), dto);
            }
        }

        return statistics.values();
    }

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {
        map.put("user", mapController.getUserID());

        return "statistics";
    }

    @RequestMapping(value = "/index")
    public String statistics() {
        return "redirect:./";
    }
}
