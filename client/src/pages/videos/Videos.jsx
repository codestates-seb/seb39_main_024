import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { selectedVideoState } from '../../recoil/atoms/videoState';
import VideoList from './VideoList';
import Youtube from '../../service/youtube';

export default function Videos({ query }) {
  const httpClient = axios.create({
    baseURL: `https://www.googleapis.com/youtube/v3`,
    params: { key: process.env.REACT_APP_YOUTUBE_API_KEY },
  });
  const youtube = new Youtube(httpClient);
  const navigate = useNavigate();

  const [videos, setVideos] = useState([]);
  // const [selectedVideo, setSelectedVideo] = useRecoilState(selectedVideoState);
  const videoDataHandler = useSetRecoilState(selectedVideoState);

  const selectVideo = (id) => {
    youtube
      .videoData(id) //
      .then((video) => {
        videoDataHandler(video);
        navigate('/videos/detail');
      });
  };

  useEffect(() => {
    youtube
      .search(query) //
      .then((videos) => {
        setVideos(videos);
      });
  }, []);

  return (
    <section>
      <VideoList videos={videos} onVideoClick={selectVideo} youtube={youtube} />
    </section>
  );
}
