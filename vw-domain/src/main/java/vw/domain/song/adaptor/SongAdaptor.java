package vw.domain.song.adaptor;

import lombok.RequiredArgsConstructor;
import vw.core.annotation.Adaptor;
import vw.domain.song.repository.SongRepository;

@Adaptor
@RequiredArgsConstructor
public class SongAdaptor { // 노래 레포지토리 어댑터
	private SongRepository songRepository;
}
