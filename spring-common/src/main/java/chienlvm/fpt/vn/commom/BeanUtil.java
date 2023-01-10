package chienlvm.fpt.vn.commom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class BeanUtil {

	public static <U, V> V copy(U source, V target) {
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static <U, V> V createAndCopy(U source, Class<V> clazz) {
		return copy(source, BeanUtils.instantiateClass(clazz));
	}

	public static <U, V> List<V> copyList(List<U> sources, Class<V> clazz) {
		List<V> targetList = new ArrayList<V>();
		sources.forEach(source -> {
			targetList.add(createAndCopy(source, clazz));
		});

		return targetList;

	}
}
