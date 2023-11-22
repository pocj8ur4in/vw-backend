package vw.domain.song.service;

import java.time.YearMonth;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vw.domain.song.adaptor.SongAdaptor;

@Service
@RequiredArgsConstructor
public class SongService { // 노래 서비스
	private SongAdaptor songAdaptor;
	private static final Logger logger =
			LoggerFactory.getLogger(SongService.class); // SLF4J를 활용한 로그 기록

	public String createSongSearchQuery(
			String name,
			String type,
			String artist,
			String vocalist,
			String parent,
			String sort,
			String num,
			String year,
			String month,
			String day) { // 노래 검색 쿼리 생성
		String query = "SELECT t.*\n" + "FROM vocawikdb.tbl_song t\n";
		query += addNameToSongSearchQuery(name);
		query += addTypeToSongSearchQuery(type, query);
		query += addArtistToSongSearchQuery(artist, query);
		query += addVocalistToSongSearchQuery(vocalist, query);
		query += addParentToSongSearchQuery(parent, query);
		query += addReleaseDateToSongSearchQuery(year, month, day, query);
		query += addSortToSongSearchQuery(sort);
		query += addNumToSongSearchQuery(num);

		return query;
	}

	private String addNameToSongSearchQuery(String name) { // 노래 이름 조건 추가
		if (Objects.isNull(name) || name.equals("")) { // 값이 공란인 경우
			return "";
		}

		return "WHERE song_original_name LIKE '%" + name.strip() + "%'\n";
	}

	private String addTypeToSongSearchQuery(String type, String query) { // 노래 타입 조건 추가
		if (Objects.isNull(type) || type.equals("")) { // 값이 공란인 경우
			return "";
		}

		boolean isValidType = true; // 값이 유효한지 검사
		for (String item : type.strip().split(", ")) {
			for (String validType :
					new String[] {"COVER", "MASHUP", "MUSIC_PV", "ORIGINAL", "REMASTER", "REMIX"}) {
				if (validType.equals(item.strip())) {
					isValidType = false;
					break;
				}
			}
		}

		if (isValidType) {
			return "";
		} else {
			if (query.contains("WHERE")) {
				return "AND song_type IN (" + type.strip() + ")\n";
			} else {
				return "WHERE song_type IN (" + type.strip() + ")\n";
			}
		}
	}

	private String addArtistToSongSearchQuery(String artist, String query) { // 노래 아티스트 조건 추가
		if (Objects.isNull(artist) || artist.equals("")) { // 값이 공란인 경우
			return "";
		} else {
			return ""; // 작성
		}
	}

	private String addVocalistToSongSearchQuery(String vocalist, String query) { // 노래 가수 조건 추가
		if (Objects.isNull(vocalist) || vocalist.equals("")) { // 값이 공란인 경우
			return "";
		} else {
			return ""; // 작성
		}
	}

	private String addParentToSongSearchQuery(String parent, String query) { // 노래 상위 항목 조건 추가
		if (Objects.isNull(parent) || parent.equals("")) { // 값이 공란인 경우
			return "";
		} else {
			return ""; // 작성
		}
	}

	private String addReleaseDateToSongSearchQuery(
			String year, String month, String day, String query) { // 노래 발매일 조건 추가
		if ((Objects.isNull(year) || year.equals(""))
				&& (Objects.isNull(month) || month.equals(""))
				&& (Objects.isNull(day) || day.equals(""))) { // 모든 값이 공란인 경우
			return "";
		}

		try { // 값이 숫자인지 검사
			int yearValue = Integer.parseInt(year.strip());
			int monthValue = Integer.parseInt(month.strip());
			int dayValue = Integer.parseInt(day.strip());

		} catch (NumberFormatException e) {
			return "";
		}

		if (day.equals("") && month.equals("")) { // 년도이 있는 경우
			if (query.contains("WHERE")) {
				return "AND ('"
						+ year.strip()
						+ "-01-01' <= your_date_column <= '"
						+ year.strip()
						+ "-12-31')\n";
			} else {
				return "WHERE ('"
						+ year.strip()
						+ "-01-01' <= your_date_column <= '"
						+ year.strip()
						+ "-12-31')\n";
			}
		} else if (day.equals("")) { // 년도, 월이 있는 경우
			int lastDay =
					YearMonth.of(Integer.parseInt(year.strip()), Integer.parseInt((month.strip())))
							.lengthOfMonth();

			if (query.contains("WHERE")) {
				return "AND ('"
						+ year.strip()
						+ "-"
						+ month.strip()
						+ "-01' <= your_date_column <= '"
						+ year.strip()
						+ "-"
						+ month.strip()
						+ "-"
						+ lastDay
						+ "')\n";
			} else {
				return "WHERE ('"
						+ year.strip()
						+ "-"
						+ month.strip()
						+ "-01' <= your_date_column <= '"
						+ year.strip()
						+ "-"
						+ month.strip()
						+ "-"
						+ lastDay
						+ "')\n";
			}
		} else { // 년도, 월, 일이 있는 경우
			if (query.contains("WHERE")) {
				return "AND (your_date_column = '"
						+ year.strip()
						+ "-"
						+ month.strip()
						+ "-"
						+ day.strip()
						+ "')\n";
			} else {
				return "WHERE (your_date_column = '"
						+ year.strip()
						+ "-"
						+ month.strip()
						+ "-"
						+ day.strip()
						+ "')\n";
			}
		}
	}

	private String addSortToSongSearchQuery(String sort) { // 노래 정렬 조건 추가
		return switch (sort) { // 정렬 조건에 따라 쿼리 생성
			case "name" -> "ORDER BY song_original_name\n";
			case "published_upper" -> "ORDER BY song_release_date DESC\n";
			case "published_lower" -> "ORDER BY song_release_date ASC\n";
			case "created_upper" -> "ORDER BY song_index DESC\n";
			case "created_lower" -> "ORDER BY song_index ASC\n";
			default -> "";
		};
	}

	private String addNumToSongSearchQuery(String num) { // 노래 개수 조건 추가
		if (num == null || num.equals("")) { // 값이 공란인 경우
			return "";
		}

		try { // 값이 숫자인지 검사
			int numValue = Integer.parseInt(num.trim());

			return "LIMIT " + numValue + "\n";
		} catch (NumberFormatException e) {
			return "";
		}
	}
}
