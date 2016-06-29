//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.server.datamanager;

/**
 * @author Grégory Van den Borre
 */
public class PersistentModuleConfiguration {//implements PersistentData<ModuleConfiguration>, RecordMapper<PlayerModulesConfigurationsRecord, ModuleConfiguration> {

//    private final PersistentManager persistentManager;
//
//    private static final PlayerModulesConfigurations TABLE = PlayerModulesConfigurations.PLAYER_MODULES_CONFIGURATIONS;
//
//    /**
//     * Full constructor, retrieve data from persistent context.
//     * 
//     * @param manager
//     *            Manager used with the persistent context.
//     * @param losManager
//     *            Line of sight manager.
//     * @param factory
//     *            Factory to build entities.
//     */
//    public PersistentModuleConfiguration(final PersistentManager manager) {
//        super();
//        this.persistentManager = manager;
////        Result<PlayerModulesConfigurationsRecord> data = manager.getAll(TABLE);
////        for (ModuleConfiguration r : data.map(this)) {
////
////        }
//    }
//
//    @Override
//    public ModuleConfiguration map(PlayerModulesConfigurationsRecord r) {
//        String name = r.getValue(TABLE.CONFIGURATION_NAME);
//        PlayerId id = PlayerId.get(r.getValue(TABLE.PLAYER_ID).intValue());
//        EntityType type = EntityType.get(r.getValue(TABLE.ENTITY_TYPE).intValue());
//        ActionId move = ActionId.get(r.getMove().intValue());
//        ActionId interaction = ActionId.get(r.getInteraction().intValue());
//        ActionId protect = ActionId.get(r.getProtect().intValue());
//        ActionId other1 = ActionId.get(r.getOther_1().intValue());
//        ActionId other2 = ActionId.get(r.getOther_2().intValue());
//        ActionId other3 = ActionId.get(r.getOther_3().intValue());
//        Modules m = new Modules(move, interaction, protect, other1, other2, other3);
//        return new ModuleConfiguration(name, id, type, m);
//    }
//
//    @Override
//    public void save(final ModuleConfiguration data) {
//        this.persistentManager.addModuleConfiguration(data);
//    }
//
//    @Override
//    public void update(ModuleConfiguration data) {
//        // UpdateQueryBuilder query = new
//        // UpdateQueryBuilder("modules_configuration");
//        // query.addColumn("entity_type", data.getType().getIndex());
//        // query.addColumn("configuration_name", data.getName());
//        // query.addColumn("player_id", data.getPlayer());
//        // query.addColumn("configuration", data.getModules().toString());
//        // query.addCondition("player_id", data.getPlayer());
//        // query.addCondition("configuration_name", data.getName());
//        // this.persistentManager.updat
//
//    }
}
