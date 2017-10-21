package database;

public class BaseQuery {

	public String buildQueryPeople() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p.surname, p.name, p.parentname, city.cityname, cars.car ");
		query.append("from public.peoples as p ");
		query.append("left join public.city as city ");
		query.append("on p.id = city.id ");
		query.append("left join public.cars as cars ");
		query.append("on p.id = cars.id ");
		query.append("WHERE p.surname = ?");
		query.append(" AND p.name = ?");
		query.append(" AND p.parentname = ?");
		query.append(" AND city.cityname = ?");
		query.append(" AND cars.car = ?");
		return query.toString();
	}

	public String buildQueryLogin() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT login, pass ");
		query.append("FROM admins ");
		query.append("WHERE login = ? ");
		query.append("AND pass = ?");
		return query.toString();
	}
}
