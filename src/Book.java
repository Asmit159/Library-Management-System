class Book{
        private int bookId;
        private String title;
        private String author;
        private boolean available;

        //constructor
        public Book(int bookId,String title,String author){
            this.bookId=bookId;
            this.title=title;
            this.author=author;
            this.available=true;
        }

        //Getters
        public int getId(){
            return bookId;
        }

        public String getTitle(){
            return title;
        }

        public String getAuthor(){
            return author;
        }

        public boolean isAvailable(){
            return available;
        }

        //Setters
        public void setAvailable(boolean available){
            this.available=available;
        }

        //Printing
        @Override
        public String toString(){
            return bookId+"|"+title+"|"+author+"|"+(available?"Available":"Not Available");
        }
    }
