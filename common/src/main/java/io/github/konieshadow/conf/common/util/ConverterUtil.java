package io.github.konieshadow.conf.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.konieshadow.conf.common.domain.PageResultBean;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConverterUtil {

    public static <S, T> List<T> toList(Collection<S> source, Function<S, T> converter) {
        return source.stream().map(converter).collect(Collectors.toList());
    }

    public static <T> PageResultBean<T> toPageResult(IPage<T> page) {
        return new PageResultBean<>(page.getTotal(), page.getRecords());
    }

    public static <S, T> PageResultBean<T> toPageResult(IPage<S> page, Function<S, T> converter) {
        return new PageResultBean<>(page.getTotal(), page.getRecords().stream().map(converter).collect(Collectors.toList()));
    }

}
