package coding.secondbiggernumber.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
public class RuntimeExplorer {
    private Integer availableProcessors;

    @PostConstruct
    private void initAvailableProcessors() {
        availableProcessors = Runtime.getRuntime().availableProcessors();

    }
}
