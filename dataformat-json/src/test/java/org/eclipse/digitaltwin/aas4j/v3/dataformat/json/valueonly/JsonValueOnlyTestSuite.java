package org.eclipse.digitaltwin.aas4j.v3.dataformat.json.valueonly;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite allowing to define order of test class execution for Value-Only JSON De-/Serialization tests.
 * Order is important because if run first, {@link JsonValueOnlyDeserialiserTest} causes {@link JsonValueOnlySerialiserTest} tests to fail.
 */
@Suite.SuiteClasses({JsonValueOnlySerialiserTest.class, JsonValueOnlyDeserialiserTest.class})
@RunWith(Suite.class)
public class JsonValueOnlyTestSuite {
}
