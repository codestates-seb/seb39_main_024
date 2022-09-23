import VideoItem from './VideoItem';

export default function VideoList({ videos, onVideoClick, display }) {
  return (
    <ul>
      {videos.map((video) => (
        <VideoItem
          key={video.id}
          video={video}
          onVideoClick={onVideoClick}
          display={display}
        />
      ))}
    </ul>
  );
}

// video {
//   etag:"QeAdOlGFnSBO5QmwOqFrEMlfa3Y"
//   id:
//     kind: "youtube#video"
//     videoId: "T-bVqdhqW2U"
//   kind: "youtube#searchResult"
//   snippet:
//     channelId: "UC9trbyGOOjJmMea3w6c-e2A"
//     channelTitle: "비타민신지니 VitaminJINY"
//     description: "팔뚝살 #팔뚝살빼는운동 #팔뚝살빨리빼는법 팔뚝살빼는운동 2탄 https://www.youtube.com/watch?v=l8KA8dgbUd0&t=155s ..."
//     liveBroadcastContent: "none"
//     publishTime: "2020-05-06T09:56:35Z"
//     publishedAt: "2020-05-06T09:56:35Z"
//     thumbnails:
//       default: {url: 'https://i.ytimg.com/vi/T-bVqdhqW2U/default.jpg', width: 120, height: 90}
//       high: {url: 'https://i.ytimg.com/vi/T-bVqdhqW2U/hqdefault.jpg', width: 480, height: 360}
//       medium: {url: 'https://i.ytimg.com/vi/T-bVqdhqW2U/mqdefault.jpg', width: 320, height: 180}
//   title: "🔥출렁이는 팔뚝살🔥빨리 빼려면 1달만 이 루틴하세요. (팔뚝살빼는운동/팔뚝살 빨리 빼는법/팔뚝살 완전 제거 운동)"
// }
