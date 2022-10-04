import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { selectedVideoState } from '../../recoil/atoms/videoState';
import axios from 'axios';
import Youtube from '../../service/youtube';
import VideoList from './VideoList';

export default function Videos({ query, id }) {
  const httpClient = axios.create({
    baseURL: `https://www.googleapis.com/youtube/v3`,
    params: { key: process.env.REACT_APP_YOUTUBE_API_KEY },
  });
  const youtube = new Youtube(httpClient);

  const navigate = useNavigate();
  const [videos, setVideos] = useState([]);
  // const [selectedVideo, setSelectedVideo] = useRecoilState(selectedVideoState);
  const setSelectedVideo = useSetRecoilState(selectedVideoState);

  useEffect(() => {
    if (id === 'all') {
      youtube
        .workout() //
        .then((videos) => {
          setVideos(videos);
        });
    } else {
      youtube
        .search(query) //
        .then((videos) => {
          setVideos(videos);
        });
    }
  }, []);

  const selectVideo = (id) => {
    youtube
      .videoData(id) //
      .then((video) => {
        setSelectedVideo(video);
        navigate('/videos/detail');
      });
  };

  return (
    <section>
      <VideoList videos={videos} onVideoClick={selectVideo} youtube={youtube} />
    </section>
  );
}
