import axios from 'axios';
import { useEffect, useState } from 'react';
import { useRecoilState } from 'recoil';
import { videosState } from '../../recoil/atoms/Atom';
import VideoList from './VideoList';
import VideoDetail from './VideoDetail';
import Youtube from '../../service/youtube';

export default function Videos() {
  const httpClient = axios.create({
    baseURL: `https://www.googleapis.com/youtube/v3`,
    params: { key: process.env.REACT_APP_YOUTUBE_API_KEY },
  });
  const youtube = new Youtube(httpClient);

  const [videos, setVideos] = useRecoilState(videosState);
  const [selectedVideo, setSelectedVideo] = useState(null);

  const selectVideo = (video) => {
    setSelectedVideo(video);
  };

  useEffect(() => {
    youtube
      .workout() //
      .then((videos) => {
        setVideos(videos);
        console.log(videos);
      });
  }, []);
  return (
    <section>
      {selectedVideo && (
        <div>
          <VideoDetail video={selectedVideo} />
        </div>
      )}
      <div>
        <VideoList videos={videos} onVideoClick={selectVideo} />
      </div>
    </section>
  );
}
