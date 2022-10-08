import { useState } from 'react';
import { useRecoilValue } from 'recoil';

import { postsCategoryState } from '../../../recoil/selectors/postsCategoryState';
import Post from '../common/Post';
import Pagination from '../common/Pagination';

export default function Meal() {
  const [currentPage, setCurrentPage] = useState(0);
  const posts = useRecoilValue(postsCategoryState(currentPage));

  return (
    <>
      <div className="grid grid-rows-1 grid-cols-1 md:grid-rows-2 md:grid-cols-2 mt-10 md:mt-14">
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
