package io.crate.operation.scalar.arithmetic;

import io.crate.operation.scalar.AbstractScalarFunctionsTest;
import org.junit.Test;

import static io.crate.testing.SymbolMatchers.isFunction;

public class ScaleFunctionTest extends AbstractScalarFunctionsTest {

    @Test
    public void testScale() throws Exception {
        assertEvaluate("scale(cast(34 as integer))", 0);
        assertEvaluate("scale(cast(-34 as integer))", 0);
        assertEvaluate("scale(34)", 0);
        assertEvaluate("scale(34.2)", 1);
        assertEvaluate("scale(cast(34 as float))", 0);
        assertEvaluate("scale(34.00)", 0);
        assertEvaluate("scale(cast(34.2 as float))", 1);
        assertEvaluate("scale(cast(34.2098 as double))", 4);
        assertEvaluate("scale(cast(34 as long))", 0);
        assertEvaluate("scale(cast(34 as short))", 0);
        assertEvaluate("scale(cast(34 as byte))", 0);

        assertEvaluate("scale(null)", null);
        assertNormalize("scale(id)", isFunction("scale"));
    }

    @Test
    public void testInvalidType() throws Exception {
        expectedException.expectMessage("unknown function: scale(string)");
        assertEvaluate("scale('foo')", null);
    }
}