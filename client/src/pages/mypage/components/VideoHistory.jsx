import { useRecoilValue } from 'recoil';
import { videoHistoryState } from '../../../recoil/selectors/videoHistoryState';

export default function VideoHistory() {
  const data = useRecoilValue(videoHistoryState);

  return (
    <section className="p-4 rounded-[8px] w-full bg-light_pink h-full">
      <p className="md:pb-4 font-extrabold">최근 시청한 운동 영상</p>
      <ul className="flex flex-row overflow-x-scroll">
        {data.map((video) => (
          <li key={video.videoId} className="m-2 w-[200px] cursor-pointer">
            <img
              className="py-1 rounded-[8px] w-full h-[85%]"
              src={video.url}
              alt="video thumbnail"
            />
            <p className="py-1 whitespace-nowrap overflow-hidden text-ellipsis">
              {video.title}
            </p>
          </li>
        ))}
      </ul>
    </section>
  );
}
