import { useNavigate } from 'react-router-dom';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import { selectedVideoState } from '../../../recoil/atoms/videoState';
import { videoHistoryState } from '../../../recoil/selectors/videoHistoryState';
import axios from 'axios';
import Youtube from '../../../service/youtube';

export default function VideoHistory() {
  const data = useRecoilValue(videoHistoryState);

  const httpClient = axios.create({
    baseURL: `https://www.googleapis.com/youtube/v3`,
    params: { key: process.env.REACT_APP_YOUTUBE_API_KEY },
  });
  const youtube = new Youtube(httpClient);
  const navigate = useNavigate();
  const setSelectedVideo = useSetRecoilState(selectedVideoState);

  const selectVideo = (id) => {
    youtube
      .videoData(id) //
      .then((video) => {
        // console.log(video[0].id);
        setSelectedVideo(video);
        navigate('/videos/detail');
      })
      .catch((err) => console.log(err));
  };

  return (
    <section className="p-4 rounded-[8px] bg-light_pink">
      <p className="md:pb-4 font-extrabold">최근 시청한 운동 영상</p>
      <ul className="flex flex-row overflow-x-scroll">
        {data.map((video) => (
          // eslint-disable-next-line jsx-a11y/click-events-have-key-events, jsx-a11y/no-noninteractive-element-interactions
          <li
            key={video.videoId}
            className="m-2 w-[200px] cursor-pointer"
            onClick={() => selectVideo(video.videoId)}
          >
            <img
              className="py-1 rounded-[8px] h-[85%]"
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
