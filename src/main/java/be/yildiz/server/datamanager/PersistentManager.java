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

import be.yildiz.common.id.PlayerId;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.shared.building.BaseBuilding;
import be.yildiz.shared.construction.building.WaitingBuilding;
import be.yildiz.shared.construction.entity.WaitingEntity;
import be.yildiz.shared.entity.action.Action;
import be.yildiz.shared.player.Message;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.Table;

import java.util.List;

/**
 * Manage the persistent data, how data are persisted depends on the implementation(database, plain text file, serialisation...).
 *
 * @author Grégory Van den Borre
 */
public interface PersistentManager {

    /**
     * Open the persistence container.
     */
    void open();

    DataBaseConnectionProvider getProvider();

    /**
     * Close the persistence container.
     */
    void close();

    /**
     * Create a new player.
     *
     * @param login      Login value.
     * @param hashedPass Pass hashed value.
     * @param email      Player email.
     * @param playerId   Player unique Id.
     * @param city       Player city unique Id.
     * @return <code>true</code> if the operation was successful.
     */
    boolean createPlayer(String login, String hashedPass, String email, PlayerId playerId);

    /**
     * @return A list of all player waiting to be created.
     */
    List<WaitingPlayer> getPlayerWaiting();

    /**
     * Save all current executing building construction tasks.
     *
     * @param buildingList Building construction task list.
     */
    void saveBuildingTask(List<WaitingBuilding<BaseBuilding>> buildingList);

    /**
     * Save all current executing entity construction tasks.
     *
     * @param entityList Entity construction task list.
     */
    void saveEntityTask(List<WaitingEntity> entityList);

    /**
     * Retrieve all persisted building construction tasks.
     *
     * @return The list of building construction tasks.
     */
    List<TaskBuilding> retrieveBuildingTask();

    /**
     * Retrieve all persisted entity construction tasks.
     *
     * @return The list of entity construction tasks.
     */
    List<TaskEntity> retrieveEntityTask();

    List<Message> retrieveMessage(PlayerId player);

    /**
     * Persist a message.
     *
     * @param message Message to save.
     */
    void persistMessage(Message message);

    void saveActionTask(List<Action> actionList);

    //void addModuleConfiguration(ModuleConfiguration config);

    long createNewLine(Table<?> t);

    <T extends Record> Result<T> getAll(Table<T> t);

    // void insert(Table<Record> t, Collection<Field<?>> fields, Collection<?> values);

}
