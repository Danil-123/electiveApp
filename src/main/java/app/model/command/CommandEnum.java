package app.model.command;

import app.model.command.TeacherCommand.GoToJournal;
import app.model.command.TeacherCommand.ListOfcourseByTeacher;
import app.model.command.TeacherCommand.SetMarkCommand;
import app.model.command.adminCommand.*;
import app.model.command.adminCommand.CourseCommand.*;
import app.model.command.adminCommand.StudentCommand.BlockStudent;
import app.model.command.adminCommand.StudentCommand.DelStudentFromCourse;
import app.model.command.adminCommand.StudentCommand.GoToProfileStudent;
import app.model.command.adminCommand.StudentCommand.UnBlockStudent;
import app.model.command.adminCommand.TeacherCommand.ChangeTeacherCommand;
import app.model.command.loginCommand.LoginCommand;
import app.model.command.loginCommand.LogoutCommand;
import app.model.command.registerCommand.GoToRegisterPage;
import app.model.command.registerCommand.RegisterCommand;
import app.model.command.sortCommand.SortByDuration;
import app.model.command.sortCommand.SortByName;
import app.model.command.sortCommand.SortByNumOfStudent;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    COURSE_LIST {
        {
            this.command = new CourseListCommand();
        }
    },
    TOPIC_LIST {
        {
            this.command = new TopicListCommand();
        }
    },
    MAIN_PAGE {
        {
            this.command = new GoToMainCommand();
        }
    },
    PROFILE_COURSE {
        {
            this.command = new ProfileCourseCommand();
        }
    },
    ENROLL_TO_COURSE {
        {
            this.command = new EnrollToCourseCommand();
        }
    },
    USER_COURSE_LIST {
        {
            this.command = new CourseListByStudentCommand();
        }
    },
    SORT_BY_NUM_OF_STUDENT {
        {
            this.command = new SortByNumOfStudent();
        }
    },
    SORT_BY_DURATION {
        {
            this.command = new SortByDuration();
        }
    },
    SORT_BY_NAME {
        {
            this.command = new SortByName();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    }, REGISTER_PAGE {
        {
            this.command = new GoToRegisterPage();
        }
    }, EDIT_COURSE {
        {
            this.command = new EditNameOfCourseCommand();
        }
    }, DELETE_COURSE {
        {
            this.command = new DelCourseCommand();
        }
    }, DETAIL_OF_COURSE {
        {
            this.command = new DetailOfCourseCommand();
        }
    }, ADD_TOPIC {
        {
            this.command = new CreateTopic();
        }
    }, CHANGE_TEACHER {
        {
            this.command = new ChangeTeacherCommand();
        }
    }, DEL_STUDENT_FROM_COURSE {
        {
            this.command = new DelStudentFromCourse();
        }
    }, CHANGE_DESCRIPTION {
        {
            this.command = new ChangeDescription();
        }
    }, CHANGE_DURATION {
        {
            this.command = new ChangeDuration();
        }
    }, ADD_COURSE {
        {
            this.command = new CreateCourse();
        }
    }, ADMIN_STUDENTS {
        {
            this.command = new GoToProfileStudent();
        }
    }, BLOCK_ACCOUNT {
        {
            this.command = new BlockStudent();
        }
    }, UNBLOCK_ACCOUNT {
        {
            this.command = new UnBlockStudent();
        }
    }, TEACH_COURSES {
        {
            this.command = new ListOfcourseByTeacher();
        }
    }, JOURNAL {
        {
            this.command = new GoToJournal();
        }
    }, SET_MARK {
        {
            this.command = new SetMarkCommand();
        }
    }, CHANGE_PAGE {
        {
            this.command = new ChangePage();
        }
    }, DEL_TOPIC {
        {
            this.command = new DelTopic();
        }
    }, BIN_OF_TOPICS {
        {
            this.command = new DelTopicList();
        }
    }, REESTABLISH_TOPIC {
        {
            this.command = new ReestablishTopic();
        }
    }, PROFILE_FINISHED_COURSE {
        {
            this.command = new ProfileFinishedCourse();
        }
    }, CHANGE_AVATAR {
        {
            this.command = new ChangeAvatarCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}