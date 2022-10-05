import VideoItem from './VideoItem';

export default function VideoList({ videos, onVideoClick }) {
  return (
    <ul>
      {videos.map((video) => (
        <VideoItem key={video.etag} video={video} onVideoClick={onVideoClick} />
      ))}
    </ul>
  );
}
