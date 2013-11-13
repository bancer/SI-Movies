package dk.kea.si.movies.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.datafactory.DataFactory;

import dk.kea.si.movies.domain.AlternateIds;
import dk.kea.si.movies.domain.Cast;
import dk.kea.si.movies.domain.Clip;
import dk.kea.si.movies.domain.Directors;
import dk.kea.si.movies.domain.Links;
import dk.kea.si.movies.domain.Movie;
import dk.kea.si.movies.domain.Movie.Timeline;
import dk.kea.si.movies.domain.Posters;
import dk.kea.si.movies.domain.Ratings;
import dk.kea.si.movies.domain.ReleaseDates;
import dk.kea.si.movies.domain.Review;
import dk.kea.si.movies.persistence.core.PersistenceFacade;

public class DataGenerator {
	
	static Timeline[] timelines = { Timeline.COMING_SOON,
			Timeline.IN_THEATERS, Timeline.OPENING, Timeline.OTHER };
	static String[] mpaaRatings = { "G", "NC-17", "PG", "PG-13", "R",
			"Unrated" };
	static String[] criticRatings = { "Certified Fresh", "Fresh", "Rotten",
			"Spilled", "Upright" };
	static String[] freshness = { "fresh", "none", "rotten" };
	static String[] publishers = { "ABCNEWS.COM", "Arizona Republic",
			"Associated Press", "At the Movies",
			"Atlanta Journal-Constitution", "Boston Globe",
			"Chicago Reader", "Chicago Sun-Times", "Chicago Tribune",
			"Christian Science Monitor", "ChristyLemire.com",
			"CNN.com", "Dallas Morning News", "Dallas Observer",
			"Denver Post", "Denver Rocky Mountain News",
			"Deseret News, Salt Lake City", "Detroit Free Press",
			"Detroit News", "Ebert & Roeper", "Entertainment Weekly",
			"Film.com", "Globe and Mail", "Good Morning America",
			"Hollywood Reporter", "Houston Chronicle",
			"Journal News (Westchester, NY)", "L.A. Weekly",
			"Los Angeles Times", "Miami Herald",
			"Minneapolis Star Tribune", "Mr. Showbiz",
			"New York Daily News", "New York Magazine",
			"New York Observer", "New York Post", "New York Times",
			"New Yorker", "Newark Star-Ledger", "Newsday", "Newsweek",
			"NPR", "NPR\'s Fresh Air", "NPR.org", "Orlando Sentinel",
			"Philadelphia Daily News", "Philadelphia Inquirer",
			"ReelViews", "Richard Roeper.com", "RogerEbert.com",
			"Rolling Stone", "Sacramento Bee", "Salon.com",
			"San Francisco Chronicle", "San Francisco Examiner",
			"San Jose Mercury News", "Seattle Times", "Slate",
			"St. Louis Post-Dispatch", "The Atlantic",
			"The New Republic", "The Wrap", "TIME Magazine",
			"Time Out", "Time Out New York", "Toronto Star",
			"USA Today", "Variety", "Village Voice", "Vulture",
			"Wall Street Journal", "Washington Post" };
	static String[] allGenres = { "Action & Adventure", "Adult", "Animation",
			"Art House & International", "Classics", "Comedy",
			"Cult Movies", "Documentary", "Drama",
			"Faith & Spirituality", "Gay & Lesbian", "Horror",
			"Kids & Family", "Musical & Performing Arts",
			"Mystery & Suspense", "Romance",
			"Science Fiction & Fantasy", "Special Interest",
			"Sports & Fitness", "Television", "Western",
			"Anime & Manga" };

	// Usually this can be a field rather than a method variable
	private static Random rand = new Random();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataFactory df = new DataFactory();
		int startId = 900004743;
		int alternateIdStart = startId - 400000000;
		int size = 900100000-startId;
		int actorStartId = 900050024;
		for (int id = startId; id < startId + size; id++) {

			Date minDate = df.getDate(1927, 1, 1);
			Date maxDate = new Date();
			Date reviewDate = df.getDateBetween(minDate, maxDate);
			Date releaseDate = reviewDate;
			Date dvdDate = new Date(1900 + releaseDate.getYear() + 1,
					releaseDate.getMonth(), releaseDate.getDay());

			ReleaseDates releases = new ReleaseDates();
			releases.setTheater("" + ((int) (1900 + releaseDate.getYear()))
					+ "-" + releaseDate.getMonth() + "-" + releaseDate.getDay());
			releases.setDvd("" + dvdDate.getYear() + "-" + dvdDate.getMonth()
					+ "-" + dvdDate.getDay());
			releases.setMovieId(id);
			// System.out.println(releases);

			Posters posters = new Posters();
			posters.setDetailed(df.getRandomChars(55, 70));
			posters.setOriginal(df.getRandomChars(55, 70));
			posters.setProfile(df.getRandomChars(55, 70));
			posters.setThumbnail(df.getRandomChars(55, 70));
			posters.setMovieId(id);

			Directors directors = new Directors();
			directors.setName(df.getRandomChars(3, 29));

			Ratings ratings = new Ratings();
			ratings.setAudience_score(randInt(1, 100));
			ratings.setAudience_rating(df.getItem(criticRatings));
			ratings.setCritics_score(randInt(1, 100));
			ratings.setCritics_rating(df.getItem(criticRatings));
			ratings.setMovieId(id);

			AlternateIds alternateIds = new AlternateIds();
			alternateIds.setImdb("" + alternateIdStart++);
			alternateIds.setMovieId(id);

			String[] genres = constructGenres(df);

			Links links = new Links();
			links.setAlternate(df.getRandomChars(34, 98));
			links.setCast(df.getRandomChars(34, 98));
			links.setClips(df.getRandomChars(34, 98));
			links.setRel(df.getRandomChars(34, 98));
			links.setReview(df.getRandomChars(34, 98));
			links.setReviews(df.getRandomChars(34, 98));
			links.setSelf(df.getRandomChars(34, 98));
			links.setSimilar(df.getRandomChars(34, 98));
			links.setMovieId(id);
			// System.out.println(links);

			int reviewsQty = randInt(1, 20);
			// System.out.println(reviewsQty);
			ArrayList<Review> reviews = new ArrayList<Review>(reviewsQty);
			//Review[] reviewsArr = new Review[reviewsQty];
			for (int i = 0; i < reviewsQty; i++) {
				Review review = new Review();
				review.setCritic(df.getRandomChars(7, 28));
				review.setDate(((int) (1900 + releaseDate.getYear())) + "-"
						+ reviewDate.getMonth() + "-" + reviewDate.getDay());
				review.setFreshness(df.getItem(freshness));
				review.setPublication(df.getItem(publishers));
				review.setQuote(df.getRandomChars(4, 256));
				review.setLinks(links);
				review.setCountry("us");
				review.setMovieId(id);
				//reviewsArr[i] = review;
				reviews.add(review);
			}
			//Reviews reviews2 = new Reviews();
			//reviews2.setReviews(reviewsArr);
			// System.out.println(reviews);

			ArrayList<Clip> clips = new ArrayList<Clip>();
			Clip clip = new Clip();
			clip.setDuration(randInt(0, 1849));
			clip.setThumbnail("" + df.getRandomChars(43, 92));
			clip.setLinks(links);
			clip.setMovieId(id);
			clips.add(clip);

			int castSize = randInt(1, 20);
			Cast[] fullCast = new Cast[castSize];
			for (int i = 0; i < castSize; i++) {
				Cast cast = new Cast();
				String[] characters = new String[] {
						"" + df.getRandomChars(1, 57),
						"" + df.getRandomChars(1, 57) };
				cast.setId(actorStartId++);
				cast.setCharacters(characters);
				cast.setName("" + df.getRandomChars(1, 41));
				fullCast[i] = cast;
			}
			// System.out.println(fullCast);

			Movie movie = new Movie();
			movie.setId(id);
			movie.setTitle(df.getRandomChars(20, 100));
			movie.setYear("" + ((int) (1900 + releaseDate.getYear())));
			movie.setTimeline(df.getItem(timelines));
			movie.setRuntime("" + randInt(1, 700));
			movie.setMpaa_rating("" + df.getItem(mpaaRatings));
			movie.setStudio(df.getRandomChars(2, 40));
			movie.setCritics_consensus(df.getRandomChars(17, 323));
			movie.setSynopsis("" + df.getRandomChars(0, 1767));

			movie.setRelease_dates(releases);
			movie.setPosters(posters);
			movie.setAbridged_directors(new Directors[] { directors });
			movie.setRatings(ratings);
			movie.setAlternate_ids(alternateIds);
			movie.setGenres(genres);
			movie.setLinks(links);
			movie.setReviews(reviews);
			movie.setClips(clips);
			movie.setFullCast(fullCast);

			//System.out.println("inserting " + id);
			// System.out.println(movie.getFullCast().length);
			// for (int i = 0; i < movie.getFullCast().length; i++) {
			// System.out.println(movie.getFullCast()[i]);
			// }
			// System.out.println(movie.getYear());
			PersistenceFacade.getInstance().insert(movie);
		}
	}

	private static String[] constructGenres(DataFactory df) {
		String[] genres = new String[2]; 
		genres[0] = df.getItem(allGenres);
		genres[1] = df.getItem(allGenres);
		while(genres[0].equals(genres[1])) {
			genres[1] = df.getItem(allGenres);
		}
		return genres;
	}

	/**
	 * Returns a psuedo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 * 
	 * @param min
	 *            Minimim value
	 * @param max
	 *            Maximim value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
