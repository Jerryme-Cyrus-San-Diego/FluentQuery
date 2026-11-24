package fluentquery;

import fluentquery.testing.PersonRepository;

public class Program {

	public static void main(String[] args) {
		var repo = new PersonRepository();

		System.out.println(repo.getPeople());
	}
}
