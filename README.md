The application can be view at: http://localhost:8080/swagger-ui/index.html

Jackson annotations in API classes does not work well with swagger-core since it will add a subclass "allOf" relation to the parent in the openAPI specification.

This means that if the parent were to contain "oneOf" and a reference to each child, then all children would recursively refer to themselves by allOf -> parent -> oneOf -> themselves

This is an issue since the Jackson SubTypes annotation is of interest to automatically establish how an incoming model should be deserialized.

When only using the swagger-core annotation (@Schema(...)) then Jackson does not know how to deserialize, so in that case the deserialization has to be specified differently (e.g. by establishing a completely custom one).

What would be the best solution to tackle this?
- Override the openAPI specification for the parent object ([custom model resolver](https://github.com/swagger-api/swagger-core/wiki/overriding-models))?
- Ignore having a nice Swagger-UI representation with @Schema and be happy with a regular oneOf annotation?
- Always implement a custom deserializer?

This project also contain a test for upgrading the OpenAPI specification from 3.0 to 3.1. Previously there has been issues
with using the @ArraySchema schema annotation, since  it works well for 3.0, but when switching over to 3.1 the "array" annotation
would disappear in the specification. A solution for this seems to be to drop @ArraySchema entirely, and instead use only
@Schema, since that annotation will still understand that it is an array without the explicit annotation.