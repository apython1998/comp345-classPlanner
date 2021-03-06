import json


def main():
    with open('../src/main/resources/courseCatalog.json', 'r') as course_JSON:  # Load courses
        course_catalog = json.load(course_JSON)
    with open('../src/main/resources/majorCatalog.json', 'r') as major_JSON:    # Load dirty majors
        major_catalog = json.load(major_JSON)
    course_lookup_table = dict()
    major_catalog_with_actual_course_dicts = []
    for course in course_catalog:
        course_lookup_table[course['courseNum']] = course                       # Make a course lookup table
    for major in major_catalog:
        new_major = dict()
        new_major['title'] = major['title']
        new_major['type'] = major['type']
        new_major_courses = []
        new_major_choose_ones = []
        for course in major['courses']:                                         # These are the string course IDs
            new_major_courses.append(course_lookup_table[course])               # Add the course objects to reqs
        for choose_one in major['grouping']:
            new_choose_one = []
            for course in choose_one:
                new_choose_one.append(course_lookup_table[course])              # Add the course object to choose ones
            new_major_choose_ones.append(new_choose_one)
        new_major['requirements'] = new_major_courses
        new_major['chooseOnes'] = new_major_choose_ones
        major_catalog_with_actual_course_dicts.append(new_major)                # Add the major with course objects
    with open('../src/main/resources/majorCatalogWithCourseObjects.json', 'w') as out_file:
        json.dump(major_catalog_with_actual_course_dicts, out_file)             # Write to file


main()
