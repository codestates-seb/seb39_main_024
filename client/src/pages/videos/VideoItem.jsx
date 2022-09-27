/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
import { memo } from 'react';

export default memo(function VideoItem({
  video,
  video: { snippet },
  onVideoClick,
}) {
  console.log(video);
  return (
    <li className="cursor-pointer" onClick={() => onVideoClick(video.id)}>
      <div className="my-5 sm:my-2">
        <img
          className="w-full"
          src={snippet.thumbnails.medium.url}
          alt="video thumbnail"
        />
        <div>
          <p className="mt-2 text-3xl sm:text-xl">{snippet.title}</p>
          <p className="mt-1 sm:text-sm">{snippet.channelTitle}</p>
        </div>
      </div>
    </li>
  );
});
