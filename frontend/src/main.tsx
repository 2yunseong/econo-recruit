import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { RouterProvider } from 'react-router';
import { createBrowserRouter } from 'react-router-dom';
import KanbanBoardPage from './page/KanbanBoard/KanbanBoard.page';
import KanbanDetailPage from './page/KanbanBoard/KanbanDetail.page';
import HomePage from './page/Home/Home.page';
import ApplicantBoardPage from './page/ApplicantBoard/ApplicantBoard.page';
import ApplicationPage from './page/Application/Application.page';
import ErrorPage from './page/Error.page';
import InterviewPage from './page/Interview/Interview.page';
import ManagerPage from './page/Manager/Manager.page';

const router = createBrowserRouter([
  {
    path: '/',
    element: <HomePage />,
  },

  {
    path: '/application/:period',
    element: <ApplicationPage />,
  },

  {
    path: '/kanban/:period',
    children: [
      { path: '/kanban/:period', element: <KanbanBoardPage /> },
      { path: '/kanban/:period/detail', element: <KanbanDetailPage /> },
    ],
  },
  {
    path: '/applicant/:period',
    children: [{ path: '/applicant/:period', element: <ApplicantBoardPage /> }],
  },
  {
    path: '/interview/:period',
    element: <InterviewPage />,
  },
  {
    path: '/manager/:period',
    element: <ManagerPage />,
  },
  {
    path: '*',
    element: <ErrorPage />,
  },
]);

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
