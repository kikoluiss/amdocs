import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import entities.Route;

public class Trains {

	static Set<Route> graph;
	static Set<String> foundTrips;
	static int shortestRoute;
	
	public static void main(String[] args) {

		graph = new HashSet<Route>() {
			{
				add(new Route("A", "B", 5));
				add(new Route("B", "C", 4));
				add(new Route("C", "D", 8));
				add(new Route("D", "C", 8));
				add(new Route("D", "E", 6));
				add(new Route("A", "D", 5));
				add(new Route("C", "E", 2));
				add(new Route("E", "B", 3));
				add(new Route("A", "E", 7));
			}
		};

				
//		1. The distance of the route A-B-C.	
		System.out.println(String.format("output #1: %s", calcRoute("A-B-C".split("-"))));
		
//		2. The distance of the route A-D.
		System.out.println(String.format("output #2: %s", calcRoute("A-D".split("-"))));
	
//		3. The distance of the route A-D-C.
		System.out.println(String.format("output #3: %s", calcRoute("A-D-C".split("-"))));
		
//		4. The distance of the route A-E-B-C-D. 
		System.out.println(String.format("output #4: %s", calcRoute("A-E-B-C-D".split("-"))));

//		5. The distance of the route A-E-D.
		System.out.println(String.format("output #5: %s", calcRoute("A-E-D".split("-"))));
	
//		6. The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such trips: C-D-C (2 stops) and C-E-B-C (3 stops).
		foundTrips = null;
		findTrips("C", "C", "", 3, false, 0);		
		System.out.println(String.format("output #6: %s", foundTrips.size()));
		
//		7. The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A to C (via B, C, D); A to C (via D, C, D); and A to C (via D, E, B).
		foundTrips = null;
		findTrips("A", "C", "", 4, true, 0);
		System.out.println(String.format("output #7: %s", foundTrips.size()));
		
//		8. The length of the shortest route (in terms of distance to travel) from A to C.
		foundTrips = null;
		findTrips("A", "C", "", 0, false, 0);
		shortestRoute = 0;
		for (String route: foundTrips) {
			int distance = Integer.valueOf(calcRoute(route.split("-")));
			if (shortestRoute == 0) {
				shortestRoute = distance;
			} else if (distance < shortestRoute) {
				shortestRoute = distance;
			}
		}
		System.out.println(String.format("output #8: %s", shortestRoute));
		
//		9. The length of the shortest route (in terms of distance to travel) from B to B.
		foundTrips = null;
		findTrips("B", "B", "", 0, false, 0);
		shortestRoute = 0;
		for (String route: foundTrips) {
			int distance = Integer.valueOf(calcRoute(route.split("-")));
			if (shortestRoute == 0) {
				shortestRoute = distance;
			} else if (distance < shortestRoute) {
				shortestRoute = distance;
			}
		}
		System.out.println(String.format("output #9: %s", shortestRoute));

//		10. The number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, and CEBCEBCEBC.
		foundTrips = null;
		findTrips("C", "C", "", 10, false, 30);
		System.out.println(String.format("output #10: %s", foundTrips.size()));
	}

	public static String calcRoute(String[] stops) {		
		if (stops.length < 2) {
			return "NO SUCH ROUTE";
		}
		
		int distance = 0;

		try {
			for (int i = 0; i < (stops.length - 1); i++) {
				final String town = stops[i];
				final String edge = stops[i + 1];
				Route currRoute = graph.stream()
					.filter(r -> (r.town.equals(town) && r.edge.equals(edge)))
					.findFirst()
					.get();
				distance += currRoute.distance;
			}
		} catch (Exception e) {
			return "NO SUCH ROUTE";
		}

		return String.format("%d", distance);
	}
	
	public static void findTrips(String start, String end, String trip, int limit, boolean exact, int maxDistance) {
		if (foundTrips == null) {
			foundTrips = new HashSet<String>();
		}
		String currTrip = trip;
		if (currTrip.isEmpty()) {
			currTrip = start;
		} else {
			currTrip += String.format("-%s", start);
		}
		Set<Route> routes = graph.stream()
				.filter(r -> r.town.equals(start))
				.collect(Collectors.toSet());
		if (routes.size() > 0) {
			for(Route route: routes) {
				if (!route.edge.equals(end)) {
					if (limit == 0) {
						if (!trip.contains(String.format("-%s", start))) {
							findTrips(route.edge, end, currTrip, limit, exact, maxDistance);
						}
					} else {
						findTrips(route.edge, end, currTrip, limit, exact, maxDistance);
					}
				} else {
					if (maxDistance > 0) {
						try {
							String _currTrip = currTrip;
							currTrip += String.format("-%s", end);
							int distance = Integer.valueOf(calcRoute(currTrip.split("-")));
							if (distance < maxDistance) {
								foundTrips.add(currTrip);
								findTrips(route.edge, end, _currTrip, limit, exact, maxDistance);
							}
						} catch (Exception e) {
							System.out.println(currTrip);
//							e.printStackTrace();
						}
					} else {
						if (limit == 0) {
							currTrip += String.format("-%s", end);
							foundTrips.add(currTrip);
						} else {
							String[] splittedTrip = currTrip.split("-");
							int stops = splittedTrip.length;
							
							if (exact && stops == limit) {
								currTrip += String.format("-%s", end);
								foundTrips.add(currTrip);
							} else if (!exact && stops <= limit) {
								currTrip += String.format("-%s", end);
								foundTrips.add(currTrip);
							} else if (stops < limit) {
								findTrips(route.edge, end, currTrip, limit, exact, maxDistance);
							} else {
								return;
							}
						}
					}
				}
			}
		}
	}
	
}
