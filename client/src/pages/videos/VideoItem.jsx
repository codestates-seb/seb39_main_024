/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
// import { useEffect, useState } from 'react';
// import { useRecoilState } from 'recoil';
// import { videoItemState } from '../../recoil/atoms/videoItemState';

export default function VideoItem({ video, video: { snippet }, onVideoClick }) {
  return (
    <li onClick={() => onVideoClick(video)}>
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
}
