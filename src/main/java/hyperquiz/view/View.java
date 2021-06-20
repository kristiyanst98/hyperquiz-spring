package hyperquiz.view;


public class View {
    public static class UserView {
        public static interface External {

        }

        public static interface Internal extends External {

        }
    }

        public static class QuizView{
            public static interface External{

            }
            public static interface Internal extends View.QuizView.External {

            }
    }

    public static class QuizResultView{
        public static interface External{

        }
        public static interface Internal extends View.QuizView.External {

        }
    }
}
