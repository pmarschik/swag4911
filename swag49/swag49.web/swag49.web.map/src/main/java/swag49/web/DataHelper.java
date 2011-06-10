package swag49.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.*;

import javax.annotation.PostConstruct;

/**
 * @author Martin Schwengerer
 */
@Component
@Scope(value = "singleton")
public class DataHelper {

    @Autowired
    @Qualifier("buildingTypeDAO")
    private DataAccessObject<BuildingType, Long> buildingTypeDAO;

    @Autowired
    @Qualifier("buildingLevelDAO")
    private DataAccessObject<BuildingLevel, BuildingLevel.Id> buildingLevelDAO;

    @Autowired
    @Qualifier("troopTypeDAO")
    private DataAccessObject<TroopType, Long> troopTypeDAO;

    @Autowired
    @Qualifier("troopLevelDAO")
    private DataAccessObject<TroopLevel, TroopLevel.Id> troopLevelDAO;


    @PostConstruct
    @Transactional
    public void init() {
        try {

            //buildings
            //GOLDMINE
            BuildingType goldMineType = new BuildingType();
            goldMineType.setName("Goldmine");
            if (buildingTypeDAO.queryByExample(goldMineType).isEmpty()) {
                goldMineType = buildingTypeDAO.create(goldMineType);

                BuildingLevel level_0 = new BuildingLevel(goldMineType, 0);
                level_0.setResourceProduction(new ResourceValue());
                level_0.setUpgradeDuration(Long.valueOf(0));
                level_0.setBuildCosts(new ResourceValue());
                level_0.setUpkeepCosts(new ResourceValue());

                level_0 = buildingLevelDAO.create(level_0);

                BuildingLevel level_1 = new BuildingLevel(goldMineType, 1);
                level_1.setResourceProduction(new ResourceValue(0, 0, 50, 0));
                level_1.setUpgradeDuration(Long.valueOf(360000));
                level_1.setBuildCosts(new ResourceValue(50, 00, 50, 50));
                level_1.setUpkeepCosts(new ResourceValue());

                level_1 = buildingLevelDAO.create(level_1);

                BuildingLevel level_2 = new BuildingLevel(goldMineType, 2);
                level_2.setResourceProduction(new ResourceValue(0, 0, 100, 0));
                level_2.setUpgradeDuration(Long.valueOf(600000));
                level_2.setBuildCosts(new ResourceValue(50, 00, 50, 50));
                level_2.setUpkeepCosts(new ResourceValue());

                level_2 = buildingLevelDAO.create(level_2);

                BuildingLevel level_3 = new BuildingLevel(goldMineType, 3);
                level_3.setResourceProduction(new ResourceValue(0, 0, 150, 0));
                level_3.setUpgradeDuration(Long.valueOf(0));
                level_3.setBuildCosts(new ResourceValue(50, 00, 50, 50));
                level_3.setUpkeepCosts(new ResourceValue());

                level_3 = buildingLevelDAO.create(level_3);

                BuildingLevel level_4 = new BuildingLevel(goldMineType, 4);
                level_4.setResourceProduction(new ResourceValue(0, 0, 250, 0));
                level_4.setUpgradeDuration(Long.valueOf(3600000));
                level_4.setBuildCosts(new ResourceValue(150, 00, 150, 150));
                level_4.setUpkeepCosts(new ResourceValue());

                level_4 = buildingLevelDAO.create(level_4);

                goldMineType.getLevels().add(level_0);
                goldMineType.getLevels().add(level_1);
                goldMineType.getLevels().add(level_2);
                goldMineType.getLevels().add(level_3);
                goldMineType.getLevels().add(level_4);

                buildingTypeDAO.update(goldMineType);
            }
            //FARM
            BuildingType farmType = new BuildingType();
            farmType.setName("Farm");

            if (buildingTypeDAO.queryByExample(farmType).isEmpty()) {
                farmType = buildingTypeDAO.create(farmType);

                BuildingLevel level_0 = new BuildingLevel(farmType, 0);
                level_0.setResourceProduction(new ResourceValue());
                level_0.setUpgradeDuration(Long.valueOf(0));
                level_0.setBuildCosts(new ResourceValue());
                level_0.setUpkeepCosts(new ResourceValue());

                level_0 = buildingLevelDAO.create(level_0);

                BuildingLevel level_1 = new BuildingLevel(farmType, 1);
                level_1.setResourceProduction(new ResourceValue(0, 50, 0, 0));
                level_1.setUpgradeDuration(Long.valueOf(360000));
                level_1.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_1.setUpkeepCosts(new ResourceValue());

                level_1 = buildingLevelDAO.create(level_1);

                BuildingLevel level_2 = new BuildingLevel(farmType, 2);
                level_2.setResourceProduction(new ResourceValue(0, 100, 0, 0));
                level_2.setUpgradeDuration(Long.valueOf(600000));
                level_2.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_2.setUpkeepCosts(new ResourceValue());

                level_2 = buildingLevelDAO.create(level_2);

                BuildingLevel level_3 = new BuildingLevel(farmType, 3);
                level_3.setResourceProduction(new ResourceValue(0, 150, 0, 0));
                level_3.setUpgradeDuration(Long.valueOf(600000));
                level_3.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_3.setUpkeepCosts(new ResourceValue());

                level_3 = buildingLevelDAO.create(level_3);

                BuildingLevel level_4 = new BuildingLevel(farmType, 4);
                level_4.setResourceProduction(new ResourceValue(0, 250, 0, 0));
                level_4.setUpgradeDuration(Long.valueOf(3600000));
                level_4.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_4.setUpkeepCosts(new ResourceValue());

                level_4 = buildingLevelDAO.create(level_4);

                farmType.getLevels().add(level_0);
                farmType.getLevels().add(level_1);
                farmType.getLevels().add(level_2);
                farmType.getLevels().add(level_3);
                farmType.getLevels().add(level_4);

                buildingTypeDAO.update(farmType);
            }

            //wood cutter
            BuildingType woodCutterType = new BuildingType();
            woodCutterType.setName("Wood Cutter");

            if (buildingTypeDAO.queryByExample(woodCutterType).isEmpty()) {
                woodCutterType = buildingTypeDAO.create(woodCutterType);

                BuildingLevel level_0 = new BuildingLevel(woodCutterType, 0);
                level_0.setResourceProduction(new ResourceValue());
                level_0.setUpgradeDuration(Long.valueOf(0));
                level_0.setBuildCosts(new ResourceValue());
                level_0.setUpkeepCosts(new ResourceValue());

                level_0 = buildingLevelDAO.create(level_0);

                BuildingLevel level_1 = new BuildingLevel(woodCutterType, 1);
                level_1.setResourceProduction(new ResourceValue(50, 0, 0, 0));
                level_1.setUpgradeDuration(Long.valueOf(360000));
                level_1.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_1.setUpkeepCosts(new ResourceValue());

                level_1 = buildingLevelDAO.create(level_1);

                BuildingLevel level_2 = new BuildingLevel(woodCutterType, 2);
                level_2.setResourceProduction(new ResourceValue(100, 0, 0, 0));
                level_2.setUpgradeDuration(Long.valueOf(600000));
                level_2.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_2.setUpkeepCosts(new ResourceValue());

                level_2 = buildingLevelDAO.create(level_2);

                BuildingLevel level_3 = new BuildingLevel(woodCutterType, 3);
                level_3.setResourceProduction(new ResourceValue(150, 0, 0, 0));
                level_3.setUpgradeDuration(Long.valueOf(600000));
                level_3.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_3.setUpkeepCosts(new ResourceValue());

                level_3 = buildingLevelDAO.create(level_3);

                BuildingLevel level_4 = new BuildingLevel(woodCutterType, 4);
                level_4.setResourceProduction(new ResourceValue(250, 0, 0, 0));
                level_4.setUpgradeDuration(Long.valueOf(3600000));
                level_4.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_4.setUpkeepCosts(new ResourceValue());

                level_4 = buildingLevelDAO.create(level_4);

                woodCutterType.getLevels().add(level_0);
                woodCutterType.getLevels().add(level_1);
                woodCutterType.getLevels().add(level_2);
                woodCutterType.getLevels().add(level_3);
                woodCutterType.getLevels().add(level_4);

                buildingTypeDAO.update(woodCutterType);
            }

            //Stone pit
            BuildingType stonePitType = new BuildingType();
            stonePitType.setName("Stone pit");

            if (buildingTypeDAO.queryByExample(stonePitType).isEmpty()) {
                stonePitType = buildingTypeDAO.create(stonePitType);

                BuildingLevel level_0 = new BuildingLevel(stonePitType, 0);
                level_0.setResourceProduction(new ResourceValue());
                level_0.setUpgradeDuration(Long.valueOf(0));
                level_0.setBuildCosts(new ResourceValue());
                level_0.setUpkeepCosts(new ResourceValue());

                level_0 = buildingLevelDAO.create(level_0);

                BuildingLevel level_1 = new BuildingLevel(stonePitType, 1);
                level_1.setResourceProduction(new ResourceValue(0, 0, 0, 50));
                level_1.setUpgradeDuration(Long.valueOf(360000));
                level_1.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_1.setUpkeepCosts(new ResourceValue());

                level_1 = buildingLevelDAO.create(level_1);

                BuildingLevel level_2 = new BuildingLevel(stonePitType, 2);
                level_2.setResourceProduction(new ResourceValue(0, 0, 0, 100));
                level_2.setUpgradeDuration(Long.valueOf(600000));
                level_2.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_2.setUpkeepCosts(new ResourceValue());

                level_2 = buildingLevelDAO.create(level_2);

                BuildingLevel level_3 = new BuildingLevel(stonePitType, 3);
                level_3.setResourceProduction(new ResourceValue(0, 0, 0, 150));
                level_3.setUpgradeDuration(Long.valueOf(600000));
                level_3.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_3.setUpkeepCosts(new ResourceValue());

                level_3 = buildingLevelDAO.create(level_3);

                BuildingLevel level_4 = new BuildingLevel(stonePitType, 4);
                level_4.setResourceProduction(new ResourceValue(0, 0, 0, 250));
                level_4.setUpgradeDuration(Long.valueOf(3600000));
                level_4.setBuildCosts(new ResourceValue(50, 0, 50, 50));
                level_4.setUpkeepCosts(new ResourceValue());

                level_4 = buildingLevelDAO.create(level_4);

                stonePitType.getLevels().add(level_0);
                stonePitType.getLevels().add(level_1);
                stonePitType.getLevels().add(level_2);
                stonePitType.getLevels().add(level_3);
                stonePitType.getLevels().add(level_4);

                buildingTypeDAO.update(stonePitType);
            }
            ////////////////////////////////////////////////////////////////
            //troops
            ////////////////////////////////////////////////////////////////

            //MONEYBOY!!!!!!!!
            TroopType moneyBoyType = new TroopType();
            moneyBoyType.setName("MoneyBoy");

            if (troopTypeDAO.queryByExample(moneyBoyType).isEmpty()) {
                moneyBoyType.setCanFoundBase(true);


                moneyBoyType = troopTypeDAO.create(moneyBoyType);

                TroopLevel troopLevel = new TroopLevel(moneyBoyType, 1);
                troopLevel.setCargo_capacity(0);
                troopLevel.setBuildCosts(new ResourceValue(200, 200, 200, 200));
                troopLevel.setSpeed(5);
                troopLevel.setStrength(5);
                troopLevel.setDefense(5);
                troopLevel.setUpkeepCosts(new ResourceValue(2, 2, 2, 2));
                troopLevel.setUpgradeDuration(Long.valueOf(3600000));

                troopLevel = troopLevelDAO.create(troopLevel);
                moneyBoyType.getLevels().add(troopLevel);
                troopTypeDAO.update(moneyBoyType);
            }

            //SWORDSMAN
            TroopType swordsmanType = new TroopType();
            swordsmanType.setName("Swordsman");
            if (troopTypeDAO.queryByExample(swordsmanType).isEmpty()) {
                swordsmanType.setCanFoundBase(false);

                swordsmanType = troopTypeDAO.create(swordsmanType);

                TroopLevel troopLevel_1 = new TroopLevel(swordsmanType, 1);
                troopLevel_1.setCargo_capacity(10);
                troopLevel_1.setBuildCosts(new ResourceValue(200, 200, 200, 0));
                troopLevel_1.setSpeed(10);
                troopLevel_1.setStrength(20);
                troopLevel_1.setDefense(20);
                troopLevel_1.setUpkeepCosts(new ResourceValue(0, 5, 5, 0));
                troopLevel_1.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_1 = troopLevelDAO.create(troopLevel_1);
                swordsmanType.getLevels().add(troopLevel_1);

                TroopLevel troopLevel_2 = new TroopLevel(swordsmanType, 2);
                troopLevel_2.setCargo_capacity(20);
                troopLevel_2.setBuildCosts(new ResourceValue(200, 200, 200, 0));
                troopLevel_2.setSpeed(10);
                troopLevel_2.setStrength(30);
                troopLevel_2.setDefense(30);
                troopLevel_2.setUpkeepCosts(new ResourceValue(0, 7, 10, 0));
                troopLevel_2.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_2 = troopLevelDAO.create(troopLevel_2);
                swordsmanType.getLevels().add(troopLevel_2);

                TroopLevel troopLevel_3 = new TroopLevel(swordsmanType, 3);
                troopLevel_3.setCargo_capacity(25);
                troopLevel_3.setBuildCosts(new ResourceValue(200, 200, 200, 0));
                troopLevel_3.setSpeed(8);
                troopLevel_3.setStrength(50);
                troopLevel_3.setDefense(40);
                troopLevel_3.setUpkeepCosts(new ResourceValue(0, 10, 15, 0));
                troopLevel_3.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_3 = troopLevelDAO.create(troopLevel_3);
                swordsmanType.getLevels().add(troopLevel_3);

                troopTypeDAO.update(swordsmanType);
            }

            //knight
            TroopType knightType = new TroopType();
            knightType.setName("Knight");

            if (troopTypeDAO.queryByExample(knightType).isEmpty()) {
                knightType.setCanFoundBase(false);

                knightType = troopTypeDAO.create(knightType);

                TroopLevel troopLevel_1 = new TroopLevel(knightType, 1);
                troopLevel_1.setCargo_capacity(15);
                troopLevel_1.setBuildCosts(new ResourceValue(200, 300, 400, 0));
                troopLevel_1.setSpeed(20);
                troopLevel_1.setStrength(20);
                troopLevel_1.setDefense(20);
                troopLevel_1.setUpkeepCosts(new ResourceValue(5, 10, 10, 0));
                troopLevel_1.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_1 = troopLevelDAO.create(troopLevel_1);
                knightType.getLevels().add(troopLevel_1);

                TroopLevel troopLevel_2 = new TroopLevel(knightType, 2);
                troopLevel_2.setCargo_capacity(20);
                troopLevel_2.setBuildCosts(new ResourceValue(200, 200, 200, 0));
                troopLevel_2.setSpeed(20);
                troopLevel_2.setStrength(50);
                troopLevel_2.setDefense(40);
                troopLevel_2.setUpkeepCosts(new ResourceValue(5, 12, 12, 0));
                troopLevel_2.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_2 = troopLevelDAO.create(troopLevel_2);
                knightType.getLevels().add(troopLevel_2);

                TroopLevel troopLevel_3 = new TroopLevel(knightType, 3);
                troopLevel_3.setCargo_capacity(30);
                troopLevel_3.setBuildCosts(new ResourceValue(200, 200, 200, 0));
                troopLevel_3.setSpeed(8);
                troopLevel_3.setStrength(70);
                troopLevel_3.setDefense(70);
                troopLevel_3.setUpkeepCosts(new ResourceValue(8, 13, 16, 0));
                troopLevel_3.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_3 = troopLevelDAO.create(troopLevel_3);
                knightType.getLevels().add(troopLevel_3);

                troopTypeDAO.update(knightType);
            }

            //samurai
            TroopType samuraiType = new TroopType();
            samuraiType.setName("Samurai");
            if (troopTypeDAO.queryByExample(samuraiType).isEmpty()) {
                samuraiType.setCanFoundBase(false);

                samuraiType = troopTypeDAO.create(samuraiType);

                TroopLevel troopLevel_1 = new TroopLevel(samuraiType, 1);
                troopLevel_1.setCargo_capacity(5);
                troopLevel_1.setBuildCosts(new ResourceValue(50, 300, 450, 0));
                troopLevel_1.setSpeed(12);
                troopLevel_1.setStrength(15);
                troopLevel_1.setDefense(10);
                troopLevel_1.setUpkeepCosts(new ResourceValue(1, 5, 5, 0));
                troopLevel_1.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_1 = troopLevelDAO.create(troopLevel_1);
                samuraiType.getLevels().add(troopLevel_1);

                TroopLevel troopLevel_2 = new TroopLevel(samuraiType, 2);
                troopLevel_2.setCargo_capacity(10);
                troopLevel_2.setBuildCosts(new ResourceValue(200, 200, 200, 0));
                troopLevel_2.setSpeed(14);
                troopLevel_2.setStrength(20);
                troopLevel_2.setDefense(15);
                troopLevel_2.setUpkeepCosts(new ResourceValue(5, 0, 20, 0));
                troopLevel_2.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_2 = troopLevelDAO.create(troopLevel_2);
                samuraiType.getLevels().add(troopLevel_2);

                TroopLevel troopLevel_3 = new TroopLevel(samuraiType, 3);
                troopLevel_3.setCargo_capacity(15);
                troopLevel_3.setBuildCosts(new ResourceValue(200, 200, 200, 0));
                troopLevel_3.setSpeed(14);
                troopLevel_3.setStrength(30);
                troopLevel_3.setDefense(150);
                troopLevel_3.setUpkeepCosts(new ResourceValue(5, 0, 30, 0));
                troopLevel_3.setUpgradeDuration(Long.valueOf(60000));

                troopLevel_3 = troopLevelDAO.create(troopLevel_3);
                samuraiType.getLevels().add(troopLevel_3);

                troopTypeDAO.update(samuraiType);
            }

        } catch (Exception e) {
            // nothing to do
        }
    }
}
