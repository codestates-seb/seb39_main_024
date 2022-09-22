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
