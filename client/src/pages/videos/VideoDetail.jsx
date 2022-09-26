import { useRecoilValue } from 'recoil';
import { selectedVideoState } from '../../recoil/atoms/Atom';

// { video, video: { snippet } }
export default function VideoDetail() {
  const video = useRecoilValue(selectedVideoState)[0];

  return (
    <section className="w-full">
      <iframe
        title="ytplayer"
        type="text/html"
        width="100%"
        height="400px"
        src={`https://www.youtube.com/embed/${video.id}`}
        frameBorder="0"
        allowFullScreen
      ></iframe>
      <h2 className="mt-2 text-3xl sm:text-xl">{video.snippet.title}</h2>
      <h3 className="mt-1 sm:text-sm">{video.snippet.channelTitle}</h3>
      <pre className="mt-3 whitespace-pre-wrap">
        {video.snippet.description}
      </pre>
    </section>
  );
}
