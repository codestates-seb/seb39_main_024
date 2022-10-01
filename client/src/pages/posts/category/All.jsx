import { useState } from 'react';
import { useRecoilValue } from 'recoil';

import { postsAllState } from '../../../recoil/selectors/postsAllState';
import Post from '../common/Post';

export default function All() {
  const [currentPage, setCurrentPage] = useState(0);

  const posts = useRecoilValue(postsAllState(currentPage));

  return (
    <>
      <div className="flex flex-wrap justify-center items-start bg-pink">
        {posts.map((post) => (
          <Post key={post.boardId} items={post} />
        ))}
      </div>
      <div className="bg-pink text-center">
        <button
          disabled={currentPage < 1}
          onClick={() => setCurrentPage((prev) => prev - 1)}
        >
          &#8249;
        </button>
        {currentPage}
        <button
          disabled={posts.length === 0}
          onClick={() => setCurrentPage((prev) => prev + 1)}
        >
          &#8250;
        </button>
      </div>
    </>
  );
}
