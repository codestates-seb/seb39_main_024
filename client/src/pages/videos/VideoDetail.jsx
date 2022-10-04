import { selectedVideoState } from '../../recoil/atoms/videoState';
import { useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import { memberIdState } from '../../recoil/atoms/memberIdState';
import YouTube from 'react-youtube';
import instance from '../../service/request';

// { video, video: { snippet } }
export default function VideoDetail() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const video = useRecoilValue(selectedVideoState)[0];
  const viewCount = video.statistics.viewCount.replace(
    /\B(?=(\d{3})+(?!\d))/g,
    ','
  );
  const date = video.snippet.publishedAt;
  const likeCount = video.statistics.likeCount.replace(
    /\B(?=(\d{3})+(?!\d))/g,
    ','
  );
  const memberId = useRecoilValue(memberIdState);
  // console.log(memberId);

  let startDate;
  let stopDate;
  let sec;

  function onPlay() {
    startDate = new Date();
  }

  function onPause() {
    stopDate = new Date();
    sec = (stopDate.getTime() - startDate.getTime()) / 1000;
    instance.post(`/record/${memberId}`, {
      record: sec,
    });
  }

  function onEnd() {
    stopDate = new Date();
    sec = (stopDate.getTime() - startDate.getTime()) / 1000;
    instance.post(`/record/${memberId}`, {
      record: sec,
    });
  }

  return (
    <section className="w-full">
      <YouTube
        id="player"
        title="ytplayer"
        type="text/html"
        videoId={video.id}
        opts={{
          width: '100%',
          height: '400vh',
          playerVars: {
            rel: 0, //방금 동영상이 재생된 채널에서 관련 동영상을 가져옴
            modestbranding: 1, // 컨트롤 바에 youtube 로고를 표시하지 않음
          },
          origin: 'http://localhost:3000',
        }}
        onPlay={onPlay}
        onPause={onPause}
        onEnd={onEnd}
      />
      <div id="videoInfo">
        <span>조회수 {viewCount}회</span>﹒
        <span>
          업로드 {date.slice(0, 4)}.{date.slice(5, 7)}.{date.slice(8, 10)}.
        </span>
        ﹒<span>♥️ {likeCount}</span>
      </div>
      <h2 className="mt-2 text-3xl sm:text-xl">{video.snippet.title}</h2>
      <h3 className="mt-1 sm:text-sm">{video.snippet.channelTitle}</h3>
      <pre className="mt-3 whitespace-pre-wrap">
        {video.snippet.description}
      </pre>
    </section>
  );
}
