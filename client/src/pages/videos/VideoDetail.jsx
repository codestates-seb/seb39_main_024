import { useRecoilValue } from 'recoil';
import { selectedVideoState } from '../../recoil/atoms/Atom';
import YouTube from 'react-youtube';
// import Timer from '../../service/timer';

// { video, video: { snippet } }
export default function VideoDetail() {
  const video = useRecoilValue(selectedVideoState)[0];
  let startDate;
  let stopDate;
  let sec;

  // function onPlayerStateChange(event) {
  //   const PlayerState = window.YT.PlayerState;
  //
  // let playerState =
  //   event.data === window.YT.PlayerState.ENDED
  //     ? '종료됨'
  //     : event.data === window.YT.PlayerState.PLAYING
  //     ? '재생 중'
  //     : event.data === window.YT.PlayerState.PAUSED
  //     ? '일시중지 됨'
  //     : event.data === window.YT.PlayerState.BUFFERING
  //     ? '버퍼링 중'
  //     : event.data === window.YT.PlayerState.CUED
  //     ? '재생준비 완료됨'
  //     : event.data === -1
  //     ? '시작되지 않음'
  //     : '예외';
  // console.log('onPlayerStateChange 실행: ' + playerState);
  //
  // 영상 재생 시작
  // if (event.data === PlayerState.PLAYING) {
  //   startDate = new Date();
  // }
  // if (event.data === PlayerState.PAUSED || event.data === PlayerState.ENDED) {
  //   stopDate = new Date();
  //   sec = (stopDate.getTime() - startDate.getTime()) / 1000;
  //   console.log(`실행시간: ${sec}`);
  // }
  // }

  function onPlay() {
    startDate = new Date();
  }
  function onPause() {
    stopDate = new Date();
    sec = (stopDate.getTime() - startDate.getTime()) / 1000;
    console.log(`실행시간: ${sec}`);
  }
  function onEnd() {
    stopDate = new Date();
    sec = (stopDate.getTime() - startDate.getTime()) / 1000;
    console.log(`실행시간: ${sec}`);
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
