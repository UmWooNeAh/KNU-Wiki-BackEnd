package uwna.knuwiki.paging;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * uwna.knuwiki.paging.QPagingDto is a Querydsl Projection type for PagingDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPagingDto extends ConstructorExpression<PagingDto> {

    private static final long serialVersionUID = -924413706L;

    public QPagingDto(com.querydsl.core.types.Expression<String> documentName, com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<String> texts) {
        super(PagingDto.class, new Class<?>[]{String.class, String.class, String.class}, documentName, memberName, texts);
    }

}

