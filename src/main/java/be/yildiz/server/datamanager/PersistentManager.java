/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.server.datamanager;

import be.yildiz.common.id.PlayerId;
import be.yildiz.module.database.DataBaseConnectionProvider;
import be.yildiz.shared.entity.action.Action;
import be.yildiz.shared.player.Message;
import org.jooq.Table;

import java.util.List;

/**
 * Manage the persistent data in database.
 *
 * @author Grégory Van den Borre
 */
public interface PersistentManager {


    /**
     * @return A list of all player waiting to be created.
     */
    List<WaitingPlayer> getPlayerWaiting();

    /**
     * Save all current executing building construction tasks.
     *
     * @param buildingList Building construction task list.
     */
    //void saveBuildingTask(List<WaitingBuilding<BaseBuilding>> buildingList);

    /**
     * Save all current executing entity construction tasks.
     *
     * @param entityList Entity construction task list.
     */
    //void saveEntityTask(List<WaitingEntity> entityList);

    /**
     * Retrieve all persisted building construction tasks.
     *
     * @return The list of building construction tasks.
     */
    //List<TaskBuilding> retrieveBuildingTask();

    /**
     * Retrieve all persisted entity construction tasks.
     *
     * @return The list of entity construction tasks.
     */
    //List<TaskEntity> retrieveEntityTask();

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

    // void insert(Table<Record> t, Collection<Field<?>> fields, Collection<?> values);

}
