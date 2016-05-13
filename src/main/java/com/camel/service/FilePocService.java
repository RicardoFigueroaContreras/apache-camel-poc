/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.camel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camel.model.FilePoc;

/**
 * @version $Revision: 356 $
 */
public class FilePocService {

    private static final Logger LOG = LoggerFactory.getLogger(FilePocService.class);

    /**
     * Convert the CSV to a model object
     */
    public FilePoc csvToObject(String csv) {
        String[] lines = csv.split(",");
        if (lines == null || lines.length != 4) {
            throw new IllegalArgumentException("CSV line is not valid: " + csv);
        }

        String supplierId = lines[0];
        String partId = lines[1];
        String name = lines[2];
        String amount = lines[3];
        FilePoc filepoc = new FilePoc(supplierId, partId, name, amount);
        LOG.info("Object :" + filepoc.toString());
        return filepoc;
    }

    /**
     * To simulate updating the inventory by calling some external system which takes a bit of time
     */
    public void updateRecords(FilePoc update) throws Exception {
        // simulate updating using some CPU processing
        Thread.sleep(5);

        LOG.info("Record " + update.getPartId() + " updated");
    }

}
