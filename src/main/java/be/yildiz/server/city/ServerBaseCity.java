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

package be.yildiz.server.city;

import be.yildiz.common.vector.Point3D;
import be.yildiz.shared.building.BaseBuilding;
import be.yildiz.shared.building.GameBuildingData;
import be.yildiz.shared.city.BaseCity;
import be.yildiz.shared.data.EntityType;
import be.yildiz.shared.entity.Entity;
import be.yildiz.shared.resources.ResourceValue;

import java.util.Map;

/**
 * @author Grégory Van den Borre
 */
public class ServerBaseCity extends BaseCity<BaseBuilding, GameBuildingData> implements ServerCity {

    /**
     * Create a new BaseCity.
     *
     * @param entity Entity for this city.
     * @param initialResource Resource when the city is built.
     * @param positionOffset  Building positions.
     * @param datas Data about the available buildings.
     */
    public ServerBaseCity(Entity entity, ResourceValue initialResource, Point3D[] positionOffset, Map<EntityType, GameBuildingData> datas) {
        super(entity, initialResource, positionOffset, datas);
    }
}
