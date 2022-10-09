import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState, useRecoilValue } from 'recoil';
import { selectedVideoState } from '../../recoil/atoms/videoState';
import { authorizationState } from '../../recoil/atoms/authorizationState';
import { memberIdState } from '../../recoil/atoms/memberIdState';
import axios from 'axios';
import Youtube from '../../service/youtube';
import instance from '../../service/request';
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
  const token = useRecoilValue(authorizationState);
  const memberId = useRecoilValue(memberIdState);

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
        // console.log(video[0].id);
        setSelectedVideo(video);
        instance.post(
          `/video`,
          {
            memberId: memberId,
            title: video[0].snippet.title,
            url: video[0].snippet.thumbnails.standard.url,
            videoId: video[0].id,
          },
          {
            headers: {
              'Content-Type': 'application/json',
              Authorization: token,
            },
          }
        );
        navigate('/videos/detail');
      })
      .catch((err) => console.log(err));
  };

  return (
    <section>
      <VideoList videos={videos} onVideoClick={selectVideo} youtube={youtube} />
    </section>
  );
}
