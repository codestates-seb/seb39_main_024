/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
export default function VideoItem({ video, video: { snippet }, onVideoClick }) {
  return (
    <li onClick={() => onVideoClick(video)}>
      <div>
        <img src={snippet.thumbnails.medium.url} alt="video thumbnail" />
        <div>
          <p>{snippet.title}</p>
          <p>{snippet.channelTitle}</p>
        </div>
      </div>
    </li>
  );
}
