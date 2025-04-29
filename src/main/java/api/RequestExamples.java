package api;

public abstract class RequestExamples {
    public static final String JSON_TYPE_SELL_PERCENTAGE = """
            {
               "type": "PERCENTAGE",
               "percentage": 100
            }
            """;
    public static final String JSON_TYPE_SELL_VALUE = """
            {
                "type": "VALUE",
                "value": 1337
            }
            """;
    public static final String JSON_TYPE_SELL_AMOUNT = """
            {
               "type": "AMOUNT",
               "amount": {
                 "value": 1.1,
                 "currency": "SEK"
               }
            }
            """;

    public static final String SELL_PERCENTAGE = """
            {
               "percentage": 100
            }
            """;
    public static final String SELL_VALUE = """
            {
                "value": 1337
            }
            """;
    public static final String SELL_AMOUNT = """
            {
               "amount": {
                 "value": 1.1,
                 "currency": "SEK"
               }
            }
            """;

    public static final String IMPLICIT_JSON_TYPE_SELL_PERCENTAGE = """
            {
               "percentage": 100
            }
            """;
    public static final String IMPLICIT_JSON_TYPE_SELL_VALUE = """
            {
                "value": 1337
            }
            """;
    public static final String IMPLICIT_JSON_TYPE_SELL_AMOUNT = """
            {
               "amount": {
                 "value": 1.1,
                 "currency": "SEK"
               }
            }
            """;

    public static final String CHILD_1 = """
            {
               "type": "CHILD_1",
               "parentField": "parentValue",
               "child1Field": "Child1Value"
            }
            """;
    public static final String CHILD_2 = """
            {
               "type": "CHILD_2",
               "parentField": "parentValue",
               "child2Field": "Child2Value"
            }
            """;
}
