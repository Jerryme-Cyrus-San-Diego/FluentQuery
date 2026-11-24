package fluentquery.builder.models;

import fluentquery.exceptions.NotSupportedTypeException;

public record Value(Object value) {

	public Value {
		var isStr = isInstance(value, String.class);
		var isInt = isInstance(value, Long.class);
		var isFlo = isInstance(value, Double.class)
				 	|| isInstance(value, Float.class);
		var isBoo = isInstance(value, Boolean.class);

		if (!isStr && !isInt && !isFlo && !isBoo) {
			throw new NotSupportedTypeException("Not supported type.");
		}
	}

	private static <T> boolean isInstance(Object val, Class<T> clazz) {
		return clazz.isInstance(val);
	}

}
