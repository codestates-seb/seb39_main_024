import { useRecoilValue } from 'recoil';
import { selectedVideoState } from '../../recoil/atoms/videoState';
import YouTube from 'react-youtube';
import instance from '../../service/request';

// { video, video: { snippet } }
export default function VideoDetail() {
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
    // console.log(`실행시간: ${sec}`);
    instance.post(`/record/${memberId}`, {
      record: sec,
    });
    // .then((res) => {
    //   console.log(res);
    // });
  }

  function onEnd() {
    stopDate = new Date();
    sec = (stopDate.getTime() - startDate.getTime()) / 1000;
    // console.log(`실행시간: ${sec}`);
    instance.post(`/record/${memberId}`, {
      record: sec,
    });
    // .then((res) => {
    //   console.log(res);
    // });
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
            rel: 0, //관련 동영상 표시하지 않음
            modestbranding: 1, // 컨트롤 바에 youtube 로고를 표시하지 않음
          },
        }}
        onPlay={onPlay}
        onPause={onPause}
        onEnd={onEnd}
        // onError={func}
        // onStateChange={onPlayerStateChange}
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
