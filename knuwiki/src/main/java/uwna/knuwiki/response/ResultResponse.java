package uwna.knuwiki.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponse<T> {
    private T result;
}
