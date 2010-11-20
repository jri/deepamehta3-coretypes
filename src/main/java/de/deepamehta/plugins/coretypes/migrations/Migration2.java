package de.deepamehta.plugins.coretypes.migrations;

import de.deepamehta.core.model.DataField;
import de.deepamehta.core.model.TopicType;
import de.deepamehta.core.service.Migration;

import java.util.logging.Logger;



/**
 * This migration adds a "Search Result" data field to the "Search Result" topic type.
 * <p>
 * This migration must run ALWAYS and we can *not* put the "Search Result" field in search-result.json.
 * Consider these cases:
 * 1) When CLEAN_INSTALL DeepaMehta 3 v0.4.3 Migration 1 runs and search-result.json *is* processed.
 * 2) When updating from DeepaMehta 3 v0.4 to v0.4.3 CLEAN_INSTALL is detected.
 * Migration 1 runs but search-result.json is *not* processed.
 * 3) When updating from DeepaMehta 3 v0.4.1 to v0.4.3 UPDATE is detected.
 * <p>
 * Distributed with deepamehta3-coretypes v0.4.2 (introduced with DeepaMehta 3 v0.4.3).
 */
public class Migration2 extends Migration {

    // ---------------------------------------------------------------------------------------------- Instance Variables

    private Logger logger = Logger.getLogger(getClass().getName());

    // -------------------------------------------------------------------------------------------------- Public Methods



    // ***************************
    // *** Implement Migration ***
    // ***************************



    /**
     * Adds a "Search Result" data field to the "Search Result" topic type.
     */
    @Override
    public void run() {
        DataField resultField = new DataField("Search Result", "reference");
        resultField.setUri("de/deepamehta/core/property/search_result");
        resultField.setRefRelationTypeId("SEARCH_RESULT");
        resultField.setEditor("checkboxes");
        //
        TopicType type = dms.getTopicType("de/deepamehta/core/topictype/SearchResult", null);   // clientContext=null
        type.addDataField(resultField);
    }
}
