package com.carter.phargate.graphql;

import graphql.scalar.GraphqlIDCoercing;
import graphql.schema.GraphQLScalarType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Scalars {

    public static GraphQLScalarType newIdScalar(String name) {
        return GraphQLScalarType.newScalar()
                .name(name)
                .coercing(new GraphqlIDCoercing())
                .build();
    }

}
