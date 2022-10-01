import { useState } from 'react';
import { useRecoilValue } from 'recoil';

import { postsAllState } from '../../../recoil/selectors/postsAllState';
import Post from '../common/Post';
import Pagination from '../../../components/Pagination';

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
      <Pagination
        posts={posts}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </>
  );
}
