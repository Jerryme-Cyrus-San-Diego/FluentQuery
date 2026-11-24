package fluentquery.builder;

public final class SqlBuilder {

	private SqlBuilder() {}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Builder() {

		}

		public SqlBuilder build() {
			return new SqlBuilder();
		}

	}



}
