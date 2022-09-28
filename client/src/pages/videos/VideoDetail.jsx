import { useRecoilValue } from 'recoil';
import { selectedVideoState } from '../../recoil/atoms/videoState';
import YouTube from 'react-youtube';
import instance from '../../service/request';
import { useEffect } from 'react';

// { video, video: { snippet } }
export default function VideoDetail() {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  const video = useRecoilValue(selectedVideoState)[0];
  let startDate;
  let stopDate;
  let sec;
  let memberId = 7;

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
      <div id="player"></div>
      <h2 className="mt-2 text-3xl sm:text-xl">{video.snippet.title}</h2>
      <h3 className="mt-1 sm:text-sm">{video.snippet.channelTitle}</h3>
      <pre className="mt-3 whitespace-pre-wrap">
        {video.snippet.description}
      </pre>
    </section>
  );
}
