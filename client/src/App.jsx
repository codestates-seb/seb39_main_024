import { Suspense, lazy } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import Summary from './pages/mypage/Summary';

const Topbar = lazy(() => import('./containers/Topbar'));
// const Sidebar = lazy(() => import('./containers/Sidebar'));
const Navbar = lazy(() => import('./containers/Navbar'));
const Join = lazy(() => import('./pages/auth/Join'));
const Login = lazy(() => import('./pages/auth/Login'));
const Layout = lazy(() => import('./containers/Layout'));
const Home = lazy(() => import('./pages/Home'));
const Hits = lazy(() => import('./pages/videos/Hits'));
const Training = lazy(() => import('./pages/videos/Training'));
const Stretching = lazy(() => import('./pages/videos/Stretching'));
const SearchResult = lazy(() => import('./pages/videos/SearchResult'));
const VideoDetail = lazy(() => import('./pages/videos/VideoDetail'));
const PostsAll = lazy(() => import('./pages/posts/category/All'));
const Record = lazy(() => import('./pages/posts/category/Record'));
const Free = lazy(() => import('./pages/posts/category/Free'));
const Meal = lazy(() => import('./pages/posts/category/Meal'));
const Read = lazy(() => import('./pages/posts/common/Read'));
const Create = lazy(() => import('./pages/posts/create/Create'));
const Edit = lazy(() => import('./pages/posts/create/Edit'));
const MyPageLayout = lazy(() => import('./containers/MyPageLayout'));
const Calendar = lazy(() => import('./pages/mypage/Calendar'));
const ProfileEdit = lazy(() => import('./pages/mypage/ProfileEdit'));
const DeleteAccount = lazy(() => import('./pages/mypage/DeleteAccount'));
const Footer = lazy(() => import('./containers/Footer'));

function App() {
  const path = useLocation().pathname;

  return (
    <RecoilRoot>
      <Suspense fallback={<p>로딩중</p>}>
        <Topbar path={path} />
        {!path.includes('/mypage') ? (
          <>
            <Navbar path={path} />
            <Layout path={path}>
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/join" element={<Join />} />
                <Route path="/videos" element={<Hits />} />
                <Route path="/videos/training" element={<Training />} />
                <Route path="/videos/stretching" element={<Stretching />} />
                <Route
                  path="/videos/search-result"
                  element={<SearchResult />}
                />
                <Route path="/videos/detail" element={<VideoDetail />} />
                <Route path="/posts" element={<PostsAll />} />
                <Route path="/posts/free" element={<Free />} />
                <Route path="/posts/meal" element={<Meal />} />
                <Route path="/posts/record" element={<Record />} />
                <Route path="/posts/create" element={<Create />} />
                <Route path="/posts/edit" element={<Edit />} />
                <Route path="/posts/create" element={<Create />} />
                <Route path="/posts/read" element={<Read />} />
              </Routes>
            </Layout>
          </>
        ) : (
          <MyPageLayout>
            <Routes>
              <Route path="/mypage" element={<Summary />} />
              <Route path="/mypage/calendar" element={<Calendar />} />
              <Route path="/mypage/profile-edit" element={<ProfileEdit />} />
              <Route
                path="/mypage/delete-account"
                element={<DeleteAccount />}
              />
            </Routes>
          </MyPageLayout>
        )}
        <Footer path={path} />
      </Suspense>
    </RecoilRoot>
  );
}

export default App;
